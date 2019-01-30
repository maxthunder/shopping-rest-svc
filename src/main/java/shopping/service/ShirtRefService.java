package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.model.ShirtRef;
import shopping.repos.ShirtRefRepository;
import shopping.util.BadRequestException;
import shopping.util.ResourceNotFoundException;

import java.util.List;

@Service
public class ShirtRefService {

	private final IBaseDAO baseDAO;
	private final ShirtRefRepository shirtRefRepository;

	@Autowired
	public ShirtRefService(IBaseDAO baseDAO, ShirtRefRepository shirtRefRepository) {
		this.baseDAO = baseDAO;
		this.shirtRefRepository = shirtRefRepository;
	}

	public List<ShirtRef> getAllShirtRefs() {
		return shirtRefRepository.findAll();
	}

	public ShirtRef getShirtRefById(Integer shirtRefId) {
		ShirtRef shirtRef = shirtRefRepository.findByShirtRefId(shirtRefId);
		if (shirtRef == null) {
			throw new ResourceNotFoundException("Shirt Ref at ID", shirtRefId);
		}
		return shirtRef;
	}

	public ShirtRef saveShirtRef(ShirtRef shirtRef) {
		return shirtRefRepository.save(shirtRef);
	}

	public ShirtRef updateShirtRef(ShirtRef shirtRef) {
		ShirtRef existingShirtRef = shirtRefRepository.findByShirtRefId(shirtRef.getShirtRefId());
		if (existingShirtRef == null) {
			throw new ResourceNotFoundException("Shirt Ref at ID", shirtRef.getShirtRefId());
		}

		if (shirtRefRepository.findByShirtName(shirtRef.getShirtName()) != null
				&& !existingShirtRef.getShirtName().equals(shirtRef.getShirtName())) {

			throw new BadRequestException("There is a different Shirt Ref that already exists with name [" + shirtRef.getShirtName() + "]");
		}

		return shirtRefRepository.save(shirtRef);
	}

	// REVIEW: Add shirtRefStatus==DELETABLE to add further protection from accidental shirtRef deletions
	public ShirtRef deleteShirtRef(Integer shirtRefId, String shirtName) {
		ShirtRef shirtRef = shirtRefRepository.findByShirtRefId(shirtRefId);

		if (shirtRef == null) {
			throw new ResourceNotFoundException("Shirt Ref at ID", shirtRefId);
		}
		if (shirtRefRepository.findByShirtName(shirtName) != null) {
			throw new BadRequestException("Shirt Ref already exists at name [" + shirtName + "]");
		}

		baseDAO.delete(shirtRef);
		return shirtRef;
	}
}
