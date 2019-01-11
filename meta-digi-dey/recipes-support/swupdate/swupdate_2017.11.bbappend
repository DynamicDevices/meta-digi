# Copyright (C) 2016-2018 Digi International Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

do_configure_append() {
	# If Trustfence is enabled, enable the signing support in the
	# '.config' file.
	if [ "${TRUSTFENCE_SIGN}" = "1" ]; then
		echo "CONFIG_SIGNED_IMAGES=y" >> ${S}/.config
		cml1_do_configure
	fi
}

do_install_append() {
	# Copy the 'progress' binary.
	install -d ${D}${bindir}/
	install -m 0755 tools/progress_unstripped ${D}${bindir}/progress
}
