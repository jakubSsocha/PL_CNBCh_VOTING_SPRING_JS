package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.models.viewDTO.AllVotingDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.VotingDetailsDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.VotingResultDTO;

import java.util.List;

public interface VotingService {

    void create(Voting voting) throws Exception;

    Voting readById(Long id) throws Exception;

    Voting readByName(String name) throws Exception;

    List<Voting> getAllActiveVotingData();

    void edit(Voting voting) throws Exception;

    boolean checkIfClosed(Long id) throws Exception;

    boolean checkIfActive(Long id) throws Exception;

    void delete(Voting voting) throws Exception;

    void close(Voting voting) throws Exception;

    VotingResultDTO generateResultForVoting(Long id) throws Exception;

    List<AllVotingDTO> getAllVotingIdData();

    List<Voting> getAllUserClosedVoting() throws Exception;

    VotingDetailsDTO getDetailsForVoting(Long id) throws Exception;
}
