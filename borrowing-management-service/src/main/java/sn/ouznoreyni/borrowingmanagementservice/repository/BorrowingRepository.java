package sn.ouznoreyni.borrowingmanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ouznoreyni.borrowingmanagementservice.entity.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}