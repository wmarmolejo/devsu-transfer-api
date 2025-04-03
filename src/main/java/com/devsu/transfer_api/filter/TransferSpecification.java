package com.devsu.transfer_api.filter;

import com.devsu.transfer_api.model.Transfer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransferSpecification {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static Specification<Transfer> filterTickets(Long idClient, String from, String to) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates =  new ArrayList<>();
            if(idClient != null && idClient>0) {
                predicates.add(criteriaBuilder.equal(root.get("clientId"),  idClient ));
            }

            if (from != null && !from.trim().isEmpty()) {
                LocalDateTime fromDate = LocalDate.parse(from, FORMATTER).atStartOfDay();
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), fromDate));
            }

            if (to != null && !to.trim().isEmpty()) {
                LocalDateTime toDate = LocalDate.parse(to, FORMATTER).atTime(23, 59, 59);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), toDate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
