package com.test.AppConfig;

import org.hibernate.annotations.common.reflection.ReflectionManager;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.internal.MetadataImpl;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.event.internal.core.JpaAutoFlushEventListener;
import org.hibernate.jpa.event.internal.core.JpaDeleteEventListener;
import org.hibernate.jpa.event.internal.core.JpaFlushEntityEventListener;
import org.hibernate.jpa.event.internal.core.JpaFlushEventListener;
import org.hibernate.jpa.event.internal.core.JpaMergeEventListener;
import org.hibernate.jpa.event.internal.core.JpaPersistEventListener;
import org.hibernate.jpa.event.internal.core.JpaPersistOnFlushEventListener;
import org.hibernate.jpa.event.internal.core.JpaPostDeleteEventListener;
import org.hibernate.jpa.event.internal.core.JpaPostInsertEventListener;
import org.hibernate.jpa.event.internal.core.JpaPostLoadEventListener;
import org.hibernate.jpa.event.internal.core.JpaPostUpdateEventListener;
import org.hibernate.jpa.event.internal.core.JpaSaveEventListener;
import org.hibernate.jpa.event.internal.core.JpaSaveOrUpdateEventListener;
import org.hibernate.jpa.event.internal.jpa.CallbackBuilderLegacyImpl;
import org.hibernate.jpa.event.internal.jpa.CallbackRegistryImpl;
import org.hibernate.jpa.event.spi.jpa.CallbackBuilder;
import org.hibernate.jpa.event.spi.jpa.ListenerFactory;
import org.hibernate.jpa.event.spi.jpa.ListenerFactoryBuilder;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
 
/**
 * This integrator allows us to use JPA-style post op annotations on Hibernate entities.
 *
 
 * This integrator is loaded by <code>org.hibernate.integrator.internal.IntegratorServiceImpl</code> from
 * <code>META-INF/services/org.hibernate.integrator.spi.Integrator</code> file.
 *
 
 * <b>Note</b>: This code is lifted directly from <code>org.hibernate.jpa.event.spi.JpaIntegrator</code>
 *
 * @author Val Blant
 */
public class JpaAnnotationsIntegrator implements Integrator {
    private ListenerFactory jpaListenerFactory;
    private CallbackBuilder callbackBuilder;
    private CallbackRegistryImpl callbackRegistry;
 
    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
 
        this.callbackRegistry = new CallbackRegistryImpl();
        
    	// op listeners
		eventListenerRegistry.setListeners( EventType.AUTO_FLUSH, JpaAutoFlushEventListener.INSTANCE );
		eventListenerRegistry.setListeners( EventType.DELETE, new JpaDeleteEventListener() );
		eventListenerRegistry.setListeners( EventType.FLUSH_ENTITY, new JpaFlushEntityEventListener() );
		eventListenerRegistry.setListeners( EventType.FLUSH, JpaFlushEventListener.INSTANCE );
		eventListenerRegistry.setListeners( EventType.MERGE, new JpaMergeEventListener() );
		eventListenerRegistry.setListeners( EventType.PERSIST, new JpaPersistEventListener() );
		eventListenerRegistry.setListeners( EventType.PERSIST_ONFLUSH, new JpaPersistOnFlushEventListener() );
		eventListenerRegistry.setListeners( EventType.SAVE, new JpaSaveEventListener() );
		eventListenerRegistry.setListeners( EventType.SAVE_UPDATE, new JpaSaveOrUpdateEventListener() );

 
        // post op listeners
        eventListenerRegistry.prependListeners( EventType.POST_DELETE, new JpaPostDeleteEventListener(callbackRegistry) );
        eventListenerRegistry.prependListeners( EventType.POST_INSERT, new JpaPostInsertEventListener(callbackRegistry) );
        eventListenerRegistry.prependListeners( EventType.POST_LOAD, new JpaPostLoadEventListener(callbackRegistry) );
        eventListenerRegistry.prependListeners( EventType.POST_UPDATE, new JpaPostUpdateEventListener(callbackRegistry) );

        
        // handle JPA "entity listener classes"...
        final ReflectionManager reflectionManager = ( (MetadataImpl) metadata )
                .getMetadataBuildingOptions()
                .getReflectionManager();
 
        this.jpaListenerFactory = ListenerFactoryBuilder.buildListenerFactory( sessionFactory.getSessionFactoryOptions() );
        this.callbackBuilder = new CallbackBuilderLegacyImpl( jpaListenerFactory, reflectionManager );
        for ( PersistentClass persistentClass : metadata.getEntityBindings() ) {
            if ( persistentClass.getClassName() == null ) {
                // we can have non java class persisted by hibernate
                continue;
            }
            callbackBuilder.buildCallbacksForEntity( persistentClass.getClassName(), callbackRegistry );
        }
    }
 
    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        if ( callbackRegistry != null ) {
            callbackRegistry.release();
        }
        if ( callbackBuilder != null ) {
            callbackBuilder.release();
        }
        if ( jpaListenerFactory != null ) {
            jpaListenerFactory.release();
        }
    }
 
}