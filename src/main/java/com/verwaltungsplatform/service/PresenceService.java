package com.verwaltungsplatform.service;

import java.util.List;

import com.verwaltungsplatform.dto.PresenceDto;
import com.verwaltungsplatform.model.Presence;

public interface PresenceService {
	
	void savePresenceEntry (PresenceDto presenceDto);
	List<PresenceDto> getPresenceEntriesLastWeek(int userId);
	PresenceDto convertToPresenceDto(Presence presence);

}
