package com.showroommanagement.repository;

import com.showroommanagement.entity.SaleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {


    @Query("SELECT sdetail FROM SaleDetail sdetail " +
            "JOIN sdetail.product p " +
            "JOIN sdetail.customer c " +
            "JOIN p.employee e " +
            "JOIN p.brand brnd " +
            "JOIN e.department d " +
            "JOIN e.branch b " +
            "JOIN b.showroom srm " +
            "WHERE srm.name = :showroomName " +
            "AND p.model = :productModel")
    List<SaleDetail> retrieveSaleDetail(@RequestParam("showroomName") String showroomName,@RequestParam("productModel") String productModel);


    @Query("SELECT sdetail FROM SaleDetail sdetail " +
            "JOIN sdetail.product p "+
            "WHERE (:keyword IS NOT NULL AND :keyword != '' AND (p.colour LIKE CONCAT('%', :keyword, '%')" +
            "OR p.model LIKE CONCAT('%', :keyword, '%')))")
    List<SaleDetail> findByModel(@RequestParam("keyword") String keyword);


    @Query("SELECT sdetail FROM SaleDetail sdetail " +
            "JOIN sdetail.product p " +
            "WHERE (:keyword IS NULL OR :keyword = '' OR p.colour LIKE CONCAT('%', :keyword, '%'))")
    Page<SaleDetail> searchByColour(@RequestParam("keyword") String keyword, Pageable pageable);

}
