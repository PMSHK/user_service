package school.faang.user_service.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.faang.user_service.dto.event.EventDto;
import school.faang.user_service.entity.event.Event;
import school.faang.user_service.mapper.event.EventMapper;
import school.faang.user_service.repository.event.EventRepository;
import school.faang.user_service.validator.event.EventValidator;

@Component
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventValidator eventValidator;

    public EventDto create(EventDto eventDto) {
        Event eventEntity = eventMapper.toEntity(eventDto);
        eventValidator.checkIfOwnerExistsById(eventEntity.getOwner().getId());
        eventValidator.checkIfOwnerHasSkillsRequired(eventEntity);
        return eventMapper.toDto(eventRepository.save(eventEntity));
    }

}
