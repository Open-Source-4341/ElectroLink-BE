package com.hampcoders.electrolink.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public record EmailAddress(String address) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);

  public EmailAddress() {
    this(null);
  }

  public EmailAddress {
    if (address == null || address.isBlank()) {
      throw new IllegalArgumentException("Email cannot be null or blank");
    }
    if (!EMAIL_PATTERN.matcher(address).matches()) {
      throw new IllegalArgumentException("Invalid email address format");
    }
  }
}
