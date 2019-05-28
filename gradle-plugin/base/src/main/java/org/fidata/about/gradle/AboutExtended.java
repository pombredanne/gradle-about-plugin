package org.fidata.about.gradle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.fidata.about.maven.CiManagement;
import org.fidata.about.maven.IssueManagement;
import org.fidata.about.maven.LicenseExtended;
import org.fidata.about.maven.MailingList;
import org.fidata.about.maven.Organization;
import org.fidata.about.model.About;
import org.fidata.about.model.StringField;
import org.fidata.about.model.UrlField;

@JsonDeserialize(builder = AboutExtended.AboutExtendedBuilderImpl.class)
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AboutExtended extends About {
  /**
   * Versioning schema used for version field
   *
   * For example, `SemVer v2.0.0` or `CalVer`.
   * If the version was made from the date the component was provisioned,
   * value `ProvisionDate` should be used.
   */
  public StringField getVersioningSchema() {
    return getString("gradle", "versioning_schema");
  }

  @Getter(onMethod_ = {@JsonProperty("gradle_keywords")})
  @Singular
  private final List<String> keywords;

  public StringField getInceptionYear() {
    return getString("maven", "inception_year");
  }

  @Getter(onMethod_ = {@JsonProperty("maven_organization")})
  @NonNull
  private final Organization organization;

  @Getter(onMethod_ = {@JsonProperty("maven_issue_management")})
  @NonNull
  private final IssueManagement issueManagement;

  @Getter(onMethod_ = {@JsonProperty("maven_ci_management")})
  @NonNull
  private final CiManagement ciManagement;

  @Getter(onMethod_ = {@JsonProperty("maven_mailing_lists")})
  @Singular
  private final List<? extends MailingList> mailingLists;

  @SuppressWarnings("unchecked")
  @Override
  public Set<? extends LicenseExtended> getLicenses() {
    return (Set<? extends LicenseExtended>)super.getLicenses();
  }

  public UrlField getVcsConnectionUrl() {
    new UrlField(new URL())
    // TODO
  }

  public UrlField getVcsDeveloperConnectionUrl() {
    return getUrl("maven", "vcs_developer_connection_url");
  }

  public UrlField getVcsUrl() {
    return getUrl("maven", "vcs_url");
  }

  public static abstract class AboutExtendedBuilder<C extends AboutExtended, B extends AboutExtendedBuilder<C, B>> extends About.AboutBuilder<C, B> {
    @Override
    public B licenses(@NonNull final Set<? extends LicenseExtended> licenses) {
      return super.licenses(licenses);
    }
  }


  protected static final class AboutExtendedBuilderImpl extends AboutExtendedBuilder<AboutExtended, AboutExtendedBuilderImpl> {}

  public static AboutExtended readFromFile(File src) throws IOException {
    return readFromFile(src, AboutExtended.class);
  }
}
