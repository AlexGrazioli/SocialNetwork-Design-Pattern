package socialNetwork.chainOfResponsibility;

import java.util.Objects;

import socialNetwork.observer.builder.Profile;

public class SearchByName extends ProfileFinder {

	public SearchByName(ProfileFinder next) {
		super(next);
	}

	@Override
	protected boolean doMatch(Profile toSearch, Profile toCompare) {
		return Objects.equals(toSearch.getName(), toCompare.getName());
	}
}
