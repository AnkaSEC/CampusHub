package co.ankasec.campushub.service;

import co.ankasec.campushub.model.dto.ClubMemberResponseDTO;
import co.ankasec.campushub.model.dto.ClubRequestDTO;
import co.ankasec.campushub.model.dto.ClubResponseDTO;
import co.ankasec.campushub.model.entity.Club;
import co.ankasec.campushub.model.entity.ClubMembership;
import co.ankasec.campushub.model.entity.Student;
import co.ankasec.campushub.model.entity.University;
import co.ankasec.campushub.model.entity.User;
import co.ankasec.campushub.repository.ClubMembershipRepository;
import co.ankasec.campushub.repository.ClubRepository;
import co.ankasec.campushub.repository.UniversityRepository;
import co.ankasec.campushub.repository.UserRepository;
import co.ankasec.campushub.model.enums.MembershipRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UniversityRepository universityRepository;
    private final ClubMembershipRepository clubMembershipRepository;
    private final UserRepository userRepository;

    public List getAllClubs() {
        return searchClubs(null, null);
    }

    public List searchClubs(String q, UUID universityId) {
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

    public List getClubMembers(UUID clubId) {
        if (!clubRepository.existsById(clubId)) {
            throw new RuntimeException("Kulüp bulunamadı");
        }

        return clubMembershipRepository.findByClubId(clubId)
                .stream()
                .map(this::convertToMemberResponseDTO)
                .toList();
    }

    public String followClub(UUID clubId, UUID userId) {
        if (clubMembershipRepository.existsByClubIdAndStudentId(clubId, userId)) {
            throw new RuntimeException("Bu kulübü zaten takip ediyorsunuz!");
        }

        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Kulüp bulunamadı"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Student student = null;
        if (user instanceof Student s) {
            student = s;
        }

        ClubMembership membership = ClubMembership.builder()
                .club(club)
                .student(student)
                .role(MembershipRole.MEMBER)
                .joinedAt(LocalDateTime.now())
                .build();

        clubMembershipRepository.save(membership);
        return "Kulüp başarıyla takip edildi.";
    }

    public String unfollowClub(UUID clubId, UUID userId) {
        ClubMembership membership = clubMembershipRepository.findByClubIdAndStudentId(clubId, userId)
                .orElseThrow(() -> new RuntimeException("Takip kaydı bulunamadı"));

        clubMembershipRepository.delete(membership);
        return "Kulüp takipten çıkarıldı.";
    }

    private ClubMemberResponseDTO convertToMemberResponseDTO(ClubMembership membership) {
        Student student = membership.getStudent();
        return ClubMemberResponseDTO.builder()
                .membershipId(membership.getId())
                .studentId(student != null ? student.getId() : null)
                .username(student != null ? student.getUsername() : null)
                .name(student != null ? student.getName() : null)
                .photoUrl(student != null ? student.getPhotoUrl() : null)
                .department(student != null ? student.getDepartment() : null)
                .studentNumber(student != null ? student.getStudentNumber() : null)
                .role(membership.getRole())
                .joinedAt(membership.getJoinedAt())
                .build();
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