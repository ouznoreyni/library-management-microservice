package sn.ouznoreyni.borrowingmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrower;


@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long>, JpaSpecificationExecutor<Borrower> {
}