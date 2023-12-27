package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Notification;
import kln.project.officerservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer>, JpaSpecificationExecutor<Notification> {
   
    List<Notification> findTop15ByNotificationtypeInAndOriginusernameNotAndEndusernameInOrderByPostedtimeDesc(List<NotificationType> ntyps, String username, List<String> endusers);

    List<Notification> findTop10ByNotificationtypeOrderByPostedtimeDesc(NotificationType notificationType);
}
