package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopRepository extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {
}