# Contributing to litmus-java-sdk

Thanks for your interest in contributing to `litmus-java-sdk` and help improve the project! ⚡️✨


## Steps to Contribute

### **1. Sign your work with Developer Certificate of Origin**

To contribute to this project, you must agree to the Developer Certificate of Origin (DCO) for each commit you make. The DCO is a simple statement that you, as a contributor, have the legal right to make the contribution.

See the [DCO](https://developercertificate.org/) file for the full text of what you must agree to.

To successfully sign off your contribution you just add a line to every git commit message:

```git
Signed-off-by: Namkyu Park <lak9348@email.com>
```

Use your real name (sorry, no pseudonyms or anonymous contributions.)

If you set your `user.name` and `user.email` git configs, you can sign your commit automatically with `git commit -s`. You can also use git [aliases](https://git-scm.com/book/tr/v2/Git-Basics-Git-Aliases) like `git config --global alias.ci 'commit -s'`. Now you can commit with git ci and the commit will be signed.

### **2. Download additional sources**

Before you build this project, you need to generate GraphQL client codes. All you have to do is enter a one command below :)

```git
mvn generate-sources
```

After generated sources, you can check out sources in the `/target/generated-sources`