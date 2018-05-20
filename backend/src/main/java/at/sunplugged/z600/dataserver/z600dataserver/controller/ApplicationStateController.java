package at.sunplugged.z600.dataserver.z600dataserver.controller;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = CrossOriginConfig.ALLOWED_CROSS_ORIGIN, maxAge = CrossOriginConfig.MAX_AGE)
@RestController
@RequestMapping({ "/api" })
public class ApplicationStateController implements ApplicationListener<ApplicationReadyEvent> {

	private boolean applicationReady = false;

	@GetMapping(path = "/ready")
	public boolean isReady() {
		return applicationReady;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		this.applicationReady = true;
	}

}
