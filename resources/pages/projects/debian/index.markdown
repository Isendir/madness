---
title: Debian repository
---

For some selected packages ([syslog-ng][sng] 3.3, 3.4 & 3.5, and
[Zorp GPL][zorp]), I provide up-to-date packages for a number of
[Debian][debian] and [Ubuntu][ubuntu] releases - see the
<kbd>sources.list</kbd> assemby area just below. The packages will be
kept up to date for all platforms, until at least after a month of the
platforms end of life.

To see what changed between versions, please see either of the
[syslog-ng 3.3][1], [syslog-ng 3.4][2] or [syslog-ng 3.5][3]
changelogs. For Zorp, there is no changelog available online yet.

 [sng]: https://www.balabit.com/network-security/syslog-ng/opensource-logging-system/overview
 [zorp]: https://www.balabit.com/network-security/zorp-gpl/overview
 [debian]: http://www.debian.org/
 [ubuntu]: http://www.ubuntu.com/

 [1]: https://git.madhouse-project.org/debian/syslog-ng/plain/debian/changelog?h=packaging/debian/3.3
 [2]: https://git.madhouse-project.org/debian/syslog-ng/plain/debian/changelog?h=packaging/debian/3.4
 [3]: https://git.madhouse-project.org/debian/syslog-ng/plain/debian/changelog?h=packaging/debian/3.5

The repository is signed by the GPG key
[6CE70C6E8B9E0644][archive-key], which one can add to apt with the following command:

    curl https://packages.madhouse-project.org/debian/archive-key.txt | sudo apt-key add -

 [archive-key]: https://packages.madhouse-project.org/debian/archive-key.txt

Packages are available for the **i386** and **amd64** architectures on
both Debian and Ubuntu, but in addition to those, the Debian
repositories also contain *syslog-ng* packages for the **powerpc**
architecture. If there's enough interest, I can pull in more build
machines, and compile for other platforms as well.

# Choose your components!

<form class="form-inline" id="dist-select">
 <fieldset>
  <select class="inline input-xlarge" id="distro-select">
    <optgroup label="Debian">
     <option value="debian-squeeze">Debian 6.0 (Squeeze; oldstable)</option>
     <option value="debian-wheezy" selected>Debian 7.0 (Wheezy; stable)</option>
     <option value="debian-jessie">Debian 8.0 (Jessie; testing)</option>
     <option value="debian-sid">Debian unstable</option>
    </optgroup>
    <optgroup label="Ubuntu">
     <option value="ubuntu-lucid">Ubuntu 10.04 LTS (Lucid Lynx)</option>
     <option value="ubuntu-precise">Ubuntu 12.04 LTS (Precise Pangolin)</option>
     <option value="ubuntu-quantal">Ubuntu 12.10 (Quantal Quetzal)</option>
     <option value="ubuntu-raring">Ubuntu 13.04 (Raring Ringtail)</option>
     <option value="ubuntu-saucy">Ubuntu 13.10 (Saucy Salamander)</option>
     <option value="ubuntu-trusty">Ubuntu 14.04 LTS (Trusty Tahr)</option>
    </optgroup>
  </select>
  <select class="inline input-xlarge" id="sng-select">
    <option value="syslog-ng-none">No syslog-ng</option>
    <optgroup label="syslog-ng old stable">
     <option value="syslog-ng-3.3">syslog-ng 3.3</option>
    </optgroup>
    <optgroup label="syslog-ng stable">
     <option value="syslog-ng">Latest syslog-ng stable release (3.4)</option>
     <option value="syslog-ng-3.4" selected>syslog-ng 3.4</option>
    </optgroup>
    <optgroup label="syslog-ng devel">
     <option value="syslog-ng-devel">Latest syslog-ng devel release (3.5)</option>
     <option value="syslog-ng-3.5">syslog-ng 3.5</option>
    </optgroup>
  </select>
  <select class="inline input-xlarge" id="zorp-select">
    <option value="zorp-none">No zorp</option>
    <option value="zorp">zorp</option>
  </select>
 </fieldset>
</form>

    deb       http://packages.madhouse-project.org/debian   wheezy   syslog-ng-3.4
    deb-src   http://packages.madhouse-project.org/debian   wheezy   syslog-ng-3.4

<script src="/assets/asylum/js/sources.list.js" type="text/javascript"></script>

<div id="alert-box" class="alert alert-block alert-error fade in"
     style="display: none">
</div>
