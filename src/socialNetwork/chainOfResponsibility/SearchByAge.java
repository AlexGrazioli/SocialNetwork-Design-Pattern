package socialNetwork.chainOfResponsibility;

import socialNetwork.observer.builder.Profile;

public class SearchByAge extends ProfileFinder {

	public SearchByAge(ProfileFinder next) {
		super(next);
	}

	@Override
	protected boolean doMatch(Profile toSearch, Profile toCompare) {
		return toSearch.getAge() == toCompare.getAge();
	}
}
