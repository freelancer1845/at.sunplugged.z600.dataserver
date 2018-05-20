package at.sunplugged.z600.dataserver.z600dataserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.sunplugged.z600.dataserver.z600dataserver.model.Z600Setting;
import at.sunplugged.z600.dataserver.z600dataserver.service.SettingsService;

@CrossOrigin(origins = CrossOriginConfig.ALLOWED_CROSS_ORIGIN, maxAge = CrossOriginConfig.MAX_AGE)
@RestController
@RequestMapping({ "/settings/api" })
public class Z600SettingsController {

	private SettingsService service;

	@Autowired
	public Z600SettingsController(SettingsService service) {
		this.service = service;
	}

	@GetMapping(path = "/getall")
	public List<Z600Setting> getAll() {
		return service.getSettings();
	}

	@PostMapping(path = "/saveall")
	public void saveAllSettings(@RequestBody List<Z600Setting> settings) {
		settings.forEach(setting -> service.updateSetting(setting));
	}

}
