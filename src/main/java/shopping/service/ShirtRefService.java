package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.model.ShirtRef;
import shopping.repos.ShirtRefRepository;
import shopping.util.BadRequestException;
import shopping.util.ResourceNotFoundException;

import java.util.Comparator;
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

	private final Comparator<ShirtRef> SHIRT_REF_COMPARATOR =
			Comparator.comparing(ShirtRef::getShirtRefId)
			.thenComparing(ShirtRef::getShirtName)
			.thenComparing(ShirtRef::getShirtSize)
			.thenComparing(ShirtRef::getShirtStyle);

	public List<ShirtRef> getAllShirtRefs() {
		return shirtRefRepository.findAll();
	}

	public ShirtRef getShirtRefById(Integer shirtRefId) {
		return shirtRefRepository.findByShirtRefId(shirtRefId)
				.orElseThrow(() -> new ResourceNotFoundException("Shirt Ref at ID", shirtRefId));
	}

	public ShirtRef saveShirtRef(ShirtRef shirtRef) {
		return shirtRefRepository.save(shirtRef);
	}

	public ShirtRef updateShirtRef(ShirtRef shirtRef) {
		ShirtRef existingShirtRef = shirtRefRepository.findByShirtRefId(shirtRef.getShirtRefId())
				.orElseThrow(() -> new ResourceNotFoundException("Shirt Ref at ID", shirtRef.getShirtRefId()));

		if (!existingShirtRef.getShirtName().equals(shirtRef.getShirtName()) &&
				shirtRefRepository.findByShirtName(shirtRef.getShirtName()).isPresent()) {
			throw new BadRequestException("There is a different Shirt Ref that already exists with name " +
					"[" + shirtRef.getShirtName() + "]");
		}

		return shirtRefRepository.save(shirtRef);
	}

	public ShirtRef deleteShirtRef(Integer shirtRefId, String shirtName) {
		ShirtRef shirtRef = shirtRefRepository.findByShirtRefIdAndAndShirtName(shirtRefId, shirtName)
				.orElseThrow(() -> new BadRequestException("Search for shirt ref under " +
						"ID [" + shirtRefId + "] and name [" + shirtName + "] not found in database."));

		baseDAO.delete(shirtRef);
		return shirtRef;
	}
}
