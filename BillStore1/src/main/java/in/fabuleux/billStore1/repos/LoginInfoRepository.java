package in.fabuleux.billStore1.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore1.entities.LoginInfo;
import in.fabuleux.billStore1.entities.User;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long>{
	LoginInfo findByUserNameAndPassword(String username,String password);
	Optional<LoginInfo> findByUserName(String username);
}
