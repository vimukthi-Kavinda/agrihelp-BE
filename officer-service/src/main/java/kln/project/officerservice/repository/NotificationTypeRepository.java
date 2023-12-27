package kln.project.officerservice.repository;


import kln.project.officerservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationTypeRepository extends JpaRepository<NotificationType,String>, JpaSpecificationExecutor<NotificationType> {
}
