title: "How to fix archlinux (rEFInd) boot after Mac OS X upgrade"
date: "2014-01-27"
---

Yesterday I applied the Mac OS X Mavericks 10.9.1 upgrade. And of course, after rebooting, my rEFInd boot had disappeared. Damned, it was booting directly under Mac OS X.

I had installed rEFInd using `install.sh` script without any argument, as [mentionned in the documentation](http://www.rodsbooks.com/refind/installing.html#quickstart). When you do so, it installs rEFInd directly on the `/` partition of you Mac. Why not, but you have to be aware that it will be erased with Mac OS upgrades.

## First try: fail

The solution would be to run the `install.sh` script with the `--esp` option: `install.sh --esp`. It should install rEFInd on the ESP partition of you Mac so that it's not overwritten when you upgrade Mac OS X. It's the first fix I tried, without any success. The Mac was not booting anymore, only a grey screen, w00t.

To fix this grey screen, I had to boot holding the `Alt` key of the Mac. Then I had to boot under the Recovery partition. In the menu I've forced my boot disk to be my SSD. I have no idea why I had to do that, but well, my Mac was booting under Mac OS X successfully after that.

## Second try: success

As it seems that the `--esp` was not working very well for me, I tried to redo what I did in the first place: just running `install.sh` without any argument to install rEFInd. And well, it worked, my rEFInd menu is back.

Now, I know that after each upgrade of Mac OS X, I will have to run `install.sh` from rEFInd again to have the ability to boot my ArchLinux.
