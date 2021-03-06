/*-
 * Copyright (c) 2017
 * Federal Office for Information Security (BSI),
 * Godesberger Allee 185-189,
 * 53175 Bonn, Germany,
 * phone: +49 228 99 9582-0,
 * fax: +49 228 99 9582-5400,
 * e-mail: bsi@bsi.bund.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bund.bsi.tr_esor.checktool.validation;

import de.bund.bsi.tr_esor.checktool.validation.report.Reference;
import oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_.ReturnVerificationReport;


/**
 * Context data for validation of one root object.
 *
 * @author TT
 * @param <T>
 */
public abstract class ValidationContext<T>
{

  private final Reference reference;

  private final T objectToValidate;

  private final String profileName;

  private final ReturnVerificationReport returnVerificationReport;

  /**
   * Creates instance to collect data during validation of one object and its children.
   *
   * @param reference unique during the verify request
   * @param objectToValidate object to validate
   * @param returnVerificationReport
   */
  protected ValidationContext(Reference reference,
                              T objectToValidate,
                              String profileName,
                              ReturnVerificationReport returnVerificationReport)
  {
    this.reference = reference;
    this.objectToValidate = objectToValidate;
    this.profileName = profileName;
    this.returnVerificationReport = returnVerificationReport;
  }

  /**
   * Returns a class object for the type of object to validate.
   */
  public abstract Class<T> getTargetClass();

  /**
   * Returns a unique human readable description defining which object is validated here.
   */
  public Reference getReference()
  {
    return reference;
  }

  /**
   * Returns the root object which is validated.
   */
  public T getObjectToValidate()
  {
    return objectToValidate;
  }

  /**
   * Returns the profile name. That name is used for choosing validators.
   */
  public String getProfileName()
  {
    return profileName;
  }

  /**
   * Returns the ReturnVerificationReport.
   */
  public ReturnVerificationReport getReturnVerificationReport()
  {
    return returnVerificationReport;
  }

  /**
   * Returns a context indication that the object is not recognized and therefore cannot be validated.
   *
   * @param ref
   */
  public static ValidationContext<Object> forUnsupported(Reference ref, String profileName)
  {
    return new UnsupportedContext(ref, profileName);
  }

  /**
   * Subtype for nothing parsed to validate.
   */
  private static class UnsupportedContext extends ValidationContext<Object>
  {

    /**
     * Creates new instance
     *
     * @param reference
     * @param profileName
     */
    protected UnsupportedContext(Reference reference, String profileName)
    {
      super(reference, null, profileName, null);
    }

    /**
     * There is no object to be validated here, hence no class.
     */
    @Override
    public Class<Object> getTargetClass()
    {
      return null;
    }
  }
}
