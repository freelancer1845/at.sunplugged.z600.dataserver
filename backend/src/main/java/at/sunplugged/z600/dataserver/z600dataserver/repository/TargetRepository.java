package at.sunplugged.z600.dataserver.z600dataserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import at.sunplugged.z600.dataserver.z600dataserver.model.Target;

public interface TargetRepository extends JpaRepository<Target, Long> {

	public Target findByName(String name);

}
