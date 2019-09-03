#
# Copyright (C) 2012-2017 Digi International.
#
SUMMARY = "Audio packagegroup for DEY image"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

ALSA_UTILS_PKGS = " \
    alsa-utils-alsactl \
    alsa-utils-alsamixer \
    alsa-utils-amixer \
    alsa-utils-aplay \
    alsa-utils-speakertest \
"

RDEPENDS_${PN} = "\
    libasound \
    alsa-state \
    alsa-states \
    ${ALSA_UTILS_PKGS} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', \
       bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio-server pulseaudio-misc', '', d), '', d)} \
"

RDEPENDS_${PN}_append_ccimx6 = " card-detect"
