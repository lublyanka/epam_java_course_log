package com.epam.rd.contactbook;

import java.util.Arrays;

public class Contact {

    private String name;
    private ContactInfo number;
    private final Email[] emails;
    private int nextEmail = 0;
    private final Social[] socials;
    private int nextSocial = 0;

    public Contact(String contactName) {
        this.name = contactName;
        socials = new Social[5];
        emails = new Email[3];
    }

    public void rename(String newName) {
        if (newName != null && !newName.equals(""))
            name = newName;
        // else
        //   throw new IllegalArgumentException("Name can't be null or empty");
    }

    public Email addEmail(String localPart, String domain) {
        if (localPart != null
                && (domain != null && !domain.equals(""))
                && (nextEmail < 3)) {
            emails[nextEmail] = new Email(localPart + "@" + domain);
            return emails[nextEmail++];
        }
        return null;
    }


    public Email addEpamEmail(String firstname, String lastname) {
        String email = firstname + "_" + lastname + "@epam.com";

        if (nextEmail < 3) {
            emails[nextEmail] = new Email(email) {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            return emails[nextEmail++];
        }
        return null;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (this.number != null)
            return null;
        return this.number = new ContactInfo() {

            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+" + code + " " + number;
            }
        };
    }

    public Social addTwitter(String twitterId) {
        return addSocialMedia("Twitter", twitterId);
    }

    public Social addInstagram(String instagramId) {
        return addSocialMedia("Instagram", instagramId);
    }

    public Social addSocialMedia(String title, String id) {
        if (nextSocial < 5) {
            socials[nextSocial] = new Social(title, id);
            return socials[nextSocial++];
        }
        return null;
    }

    public ContactInfo[] getInfo() {
        ContactInfo[] contactInfos = new ContactInfo[10];
        int next = 0;
        contactInfos[next++] = new NameContactInfo(name);
        if (number != null)
            contactInfos[next++] = number;
        for (ContactInfo email : emails) {
            if (email != null)
                contactInfos[next++] = email;
        }
        for (ContactInfo social : socials) {
            if (social != null)
                contactInfos[next++] = social;
        }

        return Arrays.copyOf(contactInfos, next);
    }

    private class NameContactInfo implements ContactInfo {

        NameContactInfo(String contactName) {
            name = name;
        }

        @Override
        public String getTitle() {
            return "Name";
        }

        @Override
        public String getValue() {
            return name;
        }
    }

    public static class Email implements ContactInfo {
        private final String email;

        public Email(String email) {
            this.email = email;
        }

        @Override
        public String getTitle() {
            return "Email";
        }

        @Override
        public String getValue() {
            return email;
        }
    }

    public static class Social implements ContactInfo {

        private final String socialName;
        private final String socialLink;

        public Social(String socialName, String socialLink) {
            this.socialName = socialName;
            this.socialLink = socialLink;
        }

        @Override
        public String getTitle() {
            return socialName;
        }

        @Override
        public String getValue() {
            return socialLink;
        }
    }
}
