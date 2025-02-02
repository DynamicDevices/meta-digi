# Copyright (C) 2022-2024 Digi International

require u-boot-dey.inc
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

DEPENDS += "flex-native bison-native"
DEPENDS += "python3-setuptools-native"

SRCBRANCH = "v2021.10/maint"
SRCREV = "${AUTOREV}"

SRC_URI += " \
    ${@oe.utils.conditional('TRUSTFENCE_SIGN_FIT_STM', '1', 'file://fit_signature.cfg', '', d)} \
"

install_helper_files() {
	# Install dtbs from UBOOT_DEVICETREE to datadir, so that kernel
	# can use it for signing, and kernel will deploy after signs it.
	if [ -n "${UBOOT_DEVICETREE}" ]; then
		for devicetree in ${UBOOT_DEVICETREE}; do
			install -Dm 0644 ${B}/${config}/arch/arm/dts/${devicetree}.dtb ${D}${datadir}/${devicetree}.dtb
		done
	else
		bbwarn "${UBOOT_DEVICETREE} not found"
	fi
}

do_install:append() {
	# Copy additional files, so kernel can use it when creating the FIT image
	if [ "${KERNEL_IMAGETYPE}" = "fitImage" ]; then
		install_helper_files
	fi
}

COMPATIBLE_MACHINE = "(ccmp1)"
