package at.sunplugged.z600.dataserver.z600dataserver.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.sunplugged.z600.dataserver.z600dataserver.model.Target;
import at.sunplugged.z600.dataserver.z600dataserver.service.TargetService;

@CrossOrigin(origins = CrossOriginConfig.ALLOWED_CROSS_ORIGIN, maxAge = CrossOriginConfig.MAX_AGE)
@RestController
@RequestMapping({ "/targets/api" })
public class TargetController {

	private TargetService service;

	@Autowired
	public TargetController(TargetService service) {
		this.service = service;
	}

	@GetMapping(path = { "" })
	public List<Target> getTargets() {
		return service.getAllTargets();
	}

	@GetMapping(path = { "/new/{creator}" })
	public Target newTarget(@PathVariable("creator") String creator) {
		return service.createTarget(creator);
	}

	@PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public Target updateTarget(@RequestBody Target target) {
		return service.updateTarget(target);
	}

	@PostMapping(path = "/delete", consumes = "application/json", produces = "application/json")
	public void deleteTarget(@RequestBody Target target) {
		service.deleteTarget(target);
	}

	@PostMapping(path = "/addWork", consumes = "application/json")
	public void addWork(@RequestBody String jsonString) {

		Map<String, Object> map = JsonParserFactory.getJsonParser().parseMap(jsonString);

		try {
			String name = (String) map.get("name");
			Double work = Double.valueOf(map.get("work").toString());

			service.addWorkToTarget(name, work);
		} catch (JSONException e) {

		}
	}

}
