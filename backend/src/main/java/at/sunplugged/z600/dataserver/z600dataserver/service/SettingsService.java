package at.sunplugged.z600.dataserver.z600dataserver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.sunplugged.z600.dataserver.z600dataserver.model.Z600Setting;
import at.sunplugged.z600.dataserver.z600dataserver.repository.Z600SettingsRepository;

@Service
public class SettingsService {

	private Z600SettingsRepository repository;

	@Autowired
	public SettingsService(Z600SettingsRepository repository) {
		this.repository = repository;
	}

	public boolean newSetting(String key) {
		if (repository.existsById(key)) {
			return false;
		} else {
			Z600Setting setting = new Z600Setting();
			setting.setKey(key);
			setting.setValue("");
			repository.save(setting);
			return true;
		}
	}

	public Z600Setting updateSetting(Z600Setting setting) {
		return repository.save(setting);
	}

	public boolean deleteSetting(String key) {
		if (repository.existsById(key) == false) {
			return false;
		} else {
			repository.deleteById(key);
			return true;
		}
	}

	public List<String> getSettingsKeys() {
		return repository.findAll().stream().map(obj -> obj.getKey()).collect(Collectors.toList());
	}

	public List<Z600Setting> getSettings() {
		return repository.findAll();
	}

	public String getValue(String key) {
		if (repository.existsById(key)) {
			return repository.getOne(key).getValue();
		} else {
			return null;
		}
	}

	public boolean setValue(String key, String value) {
		Z600Setting setting = repository.getOne(key);
		if (setting == null) {
			return false;
		} else {
			setting.setValue(value);
			return true;
		}
	}

	public boolean changeKey(String oldKey, String newKey) {
		Z600Setting setting = repository.getOne(oldKey);
		if (setting == null || repository.existsById(newKey)) {
			return false;
		} else {
			repository.delete(setting);
			setting.setKey(newKey);
			repository.save(setting);
			return true;
		}
	}

}
