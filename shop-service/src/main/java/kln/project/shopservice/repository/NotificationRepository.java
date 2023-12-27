package kln.project.shopservice.repository;


import kln.project.shopservice.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationRepository extends JpaRepository<Notification,Integer>, JpaSpecificationExecutor<Notification> {
}
