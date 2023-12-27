package kln.project.customerservice.repository;



import kln.project.customerservice.model.entity.Notification;
import kln.project.customerservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer>, JpaSpecificationExecutor<Notification> {
    List<Notification> findTop15ByNotificationtypeAndEndusername(NotificationType notificationType, String endusr);

    List<Notification> findTop15ByNotificationtypeInAndEndusername(List<NotificationType> ntyps, String endusr);

    List<Notification> findTop15ByNotificationtype(NotificationType notificationType);

    List<Notification> findTop15ByNotificationtypeOrderByPostedtimeDesc(NotificationType notificationType);
}
