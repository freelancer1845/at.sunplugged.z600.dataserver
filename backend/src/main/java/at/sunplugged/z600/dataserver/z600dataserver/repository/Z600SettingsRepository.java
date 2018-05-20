package at.sunplugged.z600.dataserver.z600dataserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.sunplugged.z600.dataserver.z600dataserver.model.Z600Setting;

public interface Z600SettingsRepository extends JpaRepository<Z600Setting, String> {

}
