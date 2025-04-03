package com.devsu.transfer_api.repository;

import com.devsu.transfer_api.model.Account;
import com.devsu.transfer_api.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<Transfer, Long>, JpaSpecificationExecutor<Transfer> {

}
