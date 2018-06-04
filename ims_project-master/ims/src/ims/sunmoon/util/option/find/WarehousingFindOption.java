package ims.sunmoon.util.option.find;

public enum WarehousingFindOption {
	NONE("-- 선택 --"), ITEM_NAME("제품명"), CON_VER("적요");

	private String text;

	private WarehousingFindOption(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
