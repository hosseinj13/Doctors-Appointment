package com.example.DoctorsAppointment.connection;


import com.example.DoctorsAppointment.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactorySingleton {
    private SessionFactorySingleton() {
    }

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder ()
                    .configure ()
                    .build ();
            INSTANCE = new MetadataSources ( registry )
                    .addAnnotatedClass(Appointment.class)
                    .addAnnotatedClass ( AppointmentHistory.class )
                    .addAnnotatedClass(AvailableTimeSlot.class)
                    .addAnnotatedClass(Doctor.class)
                    .addAnnotatedClass(Notification.class)
                    .addAnnotatedClass(PastAppointment.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(PatientFeedback.class)
                    .addAnnotatedClass(UnavailableTimeSlot.class)
                    .buildMetadata ()
                    .buildSessionFactory ();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}