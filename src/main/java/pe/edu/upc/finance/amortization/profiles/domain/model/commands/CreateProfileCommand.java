package pe.edu.upc.finance.amortization.profiles.domain.model.commands;

/**
 * Command object representing the creation of a new user profile.
 * <p>
 * This record encapsulates the necessary fields required to create a profile,
 * including personal information, contact details, and a user identifier,
 * which links the profile to a user in an external identity management system.
 * <p>
 * The fields captured in this command are:
 * - First and last name of the user.
 * - Email address for communication and identification.
 * - Address details including street, number, city, postal code, and country.
 * - An identifier (userId) representing the user in the external identity management system.
 * <p>
 * Instances of this class are typically used in command handling services
 * to validate and create profiles in the system.
 */
public record CreateProfileCommand(String firstName, String lastName, String email,
                                   String street, String number, String city,
                                   String postalCode, String country) {
}