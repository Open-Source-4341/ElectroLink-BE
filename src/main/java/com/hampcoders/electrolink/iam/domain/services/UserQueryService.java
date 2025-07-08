package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.queries.GetAllUsersQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByIdQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByUsernameQuery query);
}
