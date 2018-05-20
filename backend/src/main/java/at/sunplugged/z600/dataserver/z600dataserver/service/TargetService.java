package at.sunplugged.z600.dataserver.z600dataserver.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.sunplugged.z600.dataserver.z600dataserver.model.Target;
import at.sunplugged.z600.dataserver.z600dataserver.repository.TargetRepository;

@Service
public class TargetService {

	private TargetRepository repository;

	@Autowired
	public TargetService(TargetRepository repository) {
		this.repository = repository;
		// if (repository.findByName("Test Target") == null) {
		// Target target = new Target();
		// target.setActive(true);
		// target.setCreatedBy("Jascha");
		// target.setCreatedOn(LocalDateTime.now());
		// target.setCrPer(0.0);
		// target.setCuPer(100.0);
		// target.setGaPer(0.0);
		// target.setInPer(0.0);
		// target.setLotId("Test-Target");
		// target.setName("Test Target");
		// target.setWork(0.0);
		// repository.save(target);
		// }

	}

	public Target createTarget(String creator) {
		Target target = new Target();
		target.setCreatedOn(LocalDateTime.now());
		target.setCreatedBy(creator);
		target.setActive(false);
		return repository.save(target);
	}

	public Target updateTarget(Target target) {
		return repository.save(target);
	}

	public void deleteTarget(Target target) {
		repository.delete(target);
	}

	public void addWorkToTarget(Target target, Double work) {
		addWorkToTarget(target.getName(), work);
	}

	public void addWorkToTarget(String name, Double work) {
		Target target = repository.findByName(name);
		if (target != null) {
			target.setWork(target.getWork() + work);
			repository.save(target);
		}
	}

	public void resetTarget(Target target) {
		target.setWork(0.0);
		repository.save(target);
	}

	public List<Target> getAllTargets() {
		return repository.findAll();
	}

}
