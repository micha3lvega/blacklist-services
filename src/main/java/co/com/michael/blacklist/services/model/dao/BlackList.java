package co.com.michael.blacklist.services.model.dao;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blacklist")
public class BlackList implements Serializable {

	private static final long serialVersionUID = 2963574426754806494L;

	@Id
	private Long id;

	@NotEmpty
	private String chain;

}