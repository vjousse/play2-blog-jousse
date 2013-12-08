title: "Archlinux on a Retina (HiDPI) MacBook Pro using Xmonad"
date: "2012-12-08"
---

I've recently installed Archlinux on the latest 15" MacBook Pro Retina (late 2013). This model seems to be known under the name "MacbookPro 11,2" (ME293xx/A) on the [Apple website](http://support.apple.com/kb/ht4132). Here is the exact configuration: 15.4"/2.0 Quad-core i7/8GB/256-Flash. The installation was a little bit painful (especially the EFI part), but somehow I found out how to have a bootable Archlinux. The hardest part (understand, the less documented one) was how to have a Desktop with a "normal" font size. Indeed, by default, the resolution of the screen is 2880x1800, thus you need very good eyes to be able to see something.

## Installation

As I said, the installation was a bit painful. I followed the [Archlinux Wiki instructions](https://wiki.archlinux.org/index.php/MacBookPro11,x). I've used the [Archboot image](https://wiki.archlinux.org/index.php/Archboot), [rEFInd](http://www.rodsbooks.com/refind/) and Grub2.

To be able to access the installer without having glitches on your screen you need to boot it adding the `nomodeset` option to the kernel. To be able to do that you need the Grub menu of the installer to show up and modify the boot options. Unfortunately, when booting directly on my USB key using the Option (alt) button at startup, the Grub menu didn't show up, just a nice black screen.

In order to have the Grub menu showing up, I needed to install rEFInd under Mac OS X first, then to not press the Option (alt) button but let it boot under rEFInd. Then I choosed the "Boot Legacy OS From HD" option, the last one, with a grey icon. And, for whatever reason, it did the trick and I was able to boot under the grub menu and add `nomodeset` to the boot options.

The current kernel is the 3.12 one and it doesn't support the Intel Iris Pro 5200 Graphic card. So for now, in order to have Xorg working, you will need to use the framebuffer (`fbdev`) driver. To do so I created a `/etc/X11/xorg.conf.d/1-monitor.conf` file with this content:

```
Section "Monitor"
        Identifier      "Monitor0"
EndSection

Section "Device"
        Identifier      "Device0"
        Driver          "fbdev"
EndSection

Section "Screen"
        Identifier      "Screen0"
        Device          "Device0"
        Monitor         "Monitor0"
EndSection
```

With the 3.12 kernel, speakers will not be working. If you want to have the speakers working, you will need to install the `linux-mainline` package from AUR or wait for the 3.13 kernel to be released. Don't forget to update the entries in your Grub or to override (symlink) your `/boot/initramfs-linux-fallback.img  /boot/initramfs-linux.img  /boot/vmlinuz-linux` files with the `linux-mainline` ones.

That's it for the installation part. You should now have a working Archlinux install but the fonts should be very small, almost unreadable.


## Fonts and screen resolution/DPI problems
