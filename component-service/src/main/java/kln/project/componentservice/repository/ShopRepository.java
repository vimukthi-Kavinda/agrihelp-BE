package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.Shop;
import kln.project.componentservice.model.entity.ShopOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {
    List<Shop> findByOwnernic(ShopOwner ownerNic);
}