package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.ClubRequestDTO;
import co.ankasec.campushub.model.dto.ClubResponseDTO;
import co.ankasec.campushub.model.entity.Club;
import co.ankasec.campushub.model.entity.University;
import co.ankasec.campushub.repository.ClubRepository;
import co.ankasec.campushub.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UniversityRepository universityRepository;

    public List<ClubResponseDTO> getAllClubs() {
        return searchClubs(null, null);
    }

    public List<ClubResponseDTO> searchClubs(String q, UUID universityId) {
        String searchTerm = (q == null || q.isBlank()) ? null : q.trim();
        return clubRepository.search(searchTerm, universityId)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public ClubResponseDTO getClubById(UUID id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kulüp bulunamadı"));
        return convertToResponseDTO(club);
    }

    public ClubResponseDTO createClub(ClubRequestDTO requestDTO) {
        University university = universityRepository.findById(requestDTO.getUniversityId())
                .orElseThrow(() -> new RuntimeException("Üniversite bulunamadı"));

        Club club = Club.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .university(university)
                .build();

        return convertToResponseDTO(clubRepository.save(club));
    }

    public ClubResponseDTO updateClub(UUID id, ClubRequestDTO requestDTO) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kulüp bulunamadı"));

        if (requestDTO.getName() != null) {
            club.setName(requestDTO.getName());
        }
        if (requestDTO.getDescription() != null) {
            club.setDescription(requestDTO.getDescription());
        }
        if (requestDTO.getImage() != null) {
            club.setImage(requestDTO.getImage());
        }
        if (requestDTO.getUniversityId() != null) {
            University university = universityRepository.findById(requestDTO.getUniversityId())
                    .orElseThrow(() -> new RuntimeException("Üniversite bulunamadı"));
            club.setUniversity(university);
        }

        return convertToResponseDTO(clubRepository.save(club));
    }

    public void deleteClub(UUID id) {
        if (!clubRepository.existsById(id)) {
            throw new RuntimeException("Kulüp bulunamadı");
        }
        clubRepository.deleteById(id);
    }

    private ClubResponseDTO convertToResponseDTO(Club club) {
        return ClubResponseDTO.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .image(club.getImage())
                .universityId(club.getUniversity() != null ? club.getUniversity().getId() : null)
                .universityName(club.getUniversity() != null ? club.getUniversity().getName() : null)
                .build();
    }
}
