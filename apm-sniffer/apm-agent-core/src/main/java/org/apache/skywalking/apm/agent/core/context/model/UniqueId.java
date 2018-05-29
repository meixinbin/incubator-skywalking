package org.apache.skywalking.apm.agent.core.context.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class UniqueId {
	private List<Long> idParts;

	public UniqueId() {
		idParts = new ArrayList<Long>();
	}

	public List<Long> getIdParts() {
		return idParts;
	}

	public void setIdParts(List<Long> idParts) {
		this.idParts = idParts;
	}

	public UniqueId addIdParts(Long idPart){
		idParts.add(idPart);
		return this;
	}

	@Override
	public String toString() {
		return "UniqueId{" +
				"idParts=" + idParts +
				'}';
	}
}
