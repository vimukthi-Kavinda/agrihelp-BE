package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Product;
import kln.project.officerservice.model.entity.ShopProduct;
import kln.project.officerservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {
    List<ShopProduct> findByShopproductidProduct(Product p);
}