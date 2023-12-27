package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationTypeRepository extends JpaRepository<NotificationType,String>, JpaSpecificationExecutor<NotificationType> {
}
