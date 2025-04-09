package com.showroommanagement.service;

import com.showroommanagement.dto.PaginationDTO;
import com.showroommanagement.dto.SaleDetailDTO;
import com.showroommanagement.entity.SaleDetail;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.SaleDetailRepository;
import com.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailService(final SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    @Transactional
    public SaleDetail createSales(final SaleDetail salesDetails) {
        return this.saleDetailRepository.save(salesDetails);
    }

    public SaleDetail retrieveSalesById(final Integer id) {
        return this.saleDetailRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<SaleDetail> retrieveSales() {
        return this.saleDetailRepository.findAll();
    }

    @Transactional
    public SaleDetail updateSalesById(final SaleDetail salesDetails, final Integer id) {
        final SaleDetail existingSalesDetails = this.saleDetailRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (salesDetails.getId() != null) {
            existingSalesDetails.setId(salesDetails.getId());
        }
        if (salesDetails.getSalesDate() != null) {
            existingSalesDetails.setSalesDate(salesDetails.getSalesDate());
        }
        if (salesDetails.getProduct() != null) {
            existingSalesDetails.setProduct(salesDetails.getProduct());
        }
        if (salesDetails.getCustomer() != null) {
            existingSalesDetails.setCustomer(salesDetails.getCustomer());
        }
        return this.saleDetailRepository.save(existingSalesDetails);
    }

    public SaleDetail removeSaleDetailById(final Integer id) {
        SaleDetail saleDetail = this.saleDetailRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.saleDetailRepository.deleteById(id);
        return saleDetail;
    }

    public List<SaleDetailDTO> retrieveSaleDetail(final String showroomName, final String productName) {
        List<SaleDetail> retrieveSales = this.saleDetailRepository.findAll();
        List<SaleDetailDTO> saleDetailDTOS = new ArrayList<>();
        for (SaleDetail salesDetails : retrieveSales) {
            SaleDetailDTO saleDetailDTO = new SaleDetailDTO();
            saleDetailDTO.setShowroomName(salesDetails.getProduct().getEmployee().getDepartment().getShowroom().getName());
            saleDetailDTO.setBrandName(salesDetails.getProduct().getBrand().getBrand());
            saleDetailDTO.setProductModel(salesDetails.getProduct().getModel());
            saleDetailDTO.setProductPrice(salesDetails.getProduct().getPrice());
            saleDetailDTO.setEmployeeName(salesDetails.getProduct().getEmployee().getName());
            saleDetailDTO.setSalesDate(salesDetails.getSalesDate());
            saleDetailDTO.setDepartmentName(salesDetails.getProduct().getEmployee().getDepartment().getName());
            saleDetailDTO.setCustomerName(salesDetails.getCustomer().getName());
            saleDetailDTO.setCustomerAddress(salesDetails.getCustomer().getAddress());
            saleDetailDTO.setBranchName(salesDetails.getProduct().getEmployee().getBranch().getBranch());
            saleDetailDTOS.add(saleDetailDTO);
        }
        return saleDetailDTOS;
    }

    public PaginationDTO pagination(final int pageIndex, final int pageSize) {
        if (pageIndex <= 0 || pageSize <= 0) {
            throw new BadRequestServiceAlertException("Page index and size must be greater than zero");
        }
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<SaleDetail> page = this.saleDetailRepository.findAll(pageable);
        return new PaginationDTO(page.getTotalPages(),page.getTotalElements(),page.getSize(),page.getContent());
    }

    public Page<SaleDetail> getPaginatedData(final int pageIndex, final int pageSize, final String sorting, final boolean direction) {
        if (pageIndex <= 0 || pageSize <= 0) {
            throw new BadRequestServiceAlertException("Page index and size must be greater than zero");
        }
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, sorting));
        return this.saleDetailRepository.findAll(pageable);
    }

    public List<SaleDetail> findByModel(String keyword) {
        List<SaleDetail> saleDetails = this.saleDetailRepository.findByModel(keyword);
        if (saleDetails.isEmpty()) {
            throw new BadRequestServiceAlertException("no data found");
        }
        return saleDetails;
    }

    public Page<SaleDetail> searchByColour(int pageIndex, int pageSize, String keyword) {
        if (pageIndex <= 0 || pageSize <= 0) {
            throw new BadRequestServiceAlertException("Page index and size must be greater than zero");
        }
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Page<SaleDetail> saleDetailsPage = this.saleDetailRepository.searchByColour(keyword, pageable);
        if (saleDetailsPage.isEmpty()) {
            throw new BadRequestServiceAlertException("no data found");
        }
        return saleDetailsPage;
    }
}
