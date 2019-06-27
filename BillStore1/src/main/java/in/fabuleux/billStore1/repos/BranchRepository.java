package in.fabuleux.billStore1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore1.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
