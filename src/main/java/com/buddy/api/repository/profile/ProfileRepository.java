package com.buddy.api.repository.profile;

import com.buddy.api.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query(value = "select * from profile where profile.member_id= :memberId", nativeQuery = true)
    Profile findByMemberId(@Param("memberId") String memberId);
}
