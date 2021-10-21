function isNumberKey(evt) {
	var charCode = (evt.witch) ? evt.witch : evt.keyCode;

	if ((charCode > 47 && charCode < 58) || charCode < 32 || charCode == 127) {
		return true;
	}

	return false;
}