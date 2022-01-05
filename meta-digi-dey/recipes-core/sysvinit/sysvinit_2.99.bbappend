# Copyright (C) 2013-2014 Digi International.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"

SRC_URI_append_ccimx6 = " file://0001-sysvinit-disable-all-cpus-but-cpu0-for-halt-reboot.patch"

do_install_append() {
	# Remove 'bootlogd' bootscript symlinks
	update-rc.d -f -r ${D} stop-bootlogd remove
	update-rc.d -f -r ${D} bootlogd remove
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
