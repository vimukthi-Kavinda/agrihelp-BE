package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.Shop;
import kln.project.shopservice.model.entity.ShopProduct;
import kln.project.shopservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {
    List<ShopProduct> findByShopproductidShop(Shop shop);
}