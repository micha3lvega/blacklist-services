package co.com.michael.blacklist.services.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.michael.blacklist.services.model.dao.BlackList;
import co.com.michael.blacklist.services.processors.BlackListServices;

@RestController
@RequestMapping("/api/v1/blacklist")
public class BlackListController {

	private BlackListServices services;

	/**
	 * Constructor de la clase BlackListServices que recibe un servicio de
	 * blacklist.
	 *
	 * @param services Instancia de BlackListServices a inyectar en el controlador.
	 */
	public BlackListController(BlackListServices services) {
		this.services = services;
	}

	/**
	 * Retorna todos los elementos de la blacklist.
	 *
	 * @return Lista de todos los elementos de la blacklist.
	 */
	@GetMapping
	public List<BlackList> findAll() {
		return services.findAll();
	}

	/**
	 * Busca una entrada en la blacklist basada en una cadena proporcionada.
	 *
	 * @param chain cadena a buscar en la blacklist.
	 * @return Objeto {@link BlackList} correspondiente a la cadena proporcionada.
	 */
	@GetMapping("/{chain}")
	public BlackList findBychain(@PathVariable("chain") String chain) {
		return services.findByChain(chain);
	}

	/**
	 * Valida si una cadena está en el diccionario o no.
	 *
	 * @param chain cadena a validar.
	 * @return true si la cadena es válida, false si no está en el diccionario.
	 */
	@PostMapping("/validate")
	public boolean isValidchain(@RequestBody String chain) {
		return services.isValidChain(chain);
	}

}
